import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { DataService } from '../../../_services/data.service';
import { ComunicationService, AlertService } from '../../../_services';
import { Description, DivisionResult, MultiplicationResult, AdditionResult, SubtractionResult, Result } from '../../../_models';
import { JsonConvert, OperationMode } from 'json2typescript';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-inputboard',
  templateUrl: './inputboard.component.html'
})
export class InputBoardComponent implements OnInit {
  inputForm: FormGroup;
  loading = false;
  submitted = false;
  firstnumber: string;
  secondnumber: string;
  result: Result;
  additionResult: AdditionResult;
  subtractionResult: SubtractionResult;
  multiplicationResult: MultiplicationResult;
  divisionResult: DivisionResult;
  jsonConvert: JsonConvert;
  id: number;
  private numberValidator = '^[0-9]{1,6}$';
  constructor(
    private formBuilder: FormBuilder,
    private data: DataService,
    private alertService: AlertService,
    private comunicationService: ComunicationService
  ) {}
  ngOnInit() {
    this.comunicationService.DescriptionMessage.subscribe((res) => {
      this.id = res.id;
      this.result = new AdditionResult();
    });
    // Check the detailed reference in the chapter "JsonConvert class properties and methods"
    this.jsonConvert = new JsonConvert();
    this.jsonConvert.operationMode = OperationMode.LOGGING; // print some debug data
    this.jsonConvert.ignorePrimitiveChecks = false; // don't allow assigning number to string etc.
    // Input data
    this.inputForm = this.formBuilder.group({
      firstnumber: ['', [Validators.required, Validators.pattern(this.numberValidator)]],
      secondnumber: ['', [Validators.required, Validators.pattern(this.numberValidator)]]
    });
  }
  // getter for access to the forms fields
  get f() {
    return this.inputForm.controls;
  }

  // formreset
  onResetClick(): void {
    this.inputForm.reset();
    // Clear alerts
    this.alertService.clear();
  }

  // Send request to the calculation service
  onSubmit() {
    this.submitted = true;
    // return if form is invalid
    if (this.inputForm.invalid) {
      return;
    }
    // Clear alerts
    this.alertService.clear();
    // Make Http request
    this.data
      .get_result(this.id, this.f.firstnumber.value, this.f.secondnumber.value)
      .subscribe(
        (res: any) => {
        // depends on selected operation type read corresponding responce
        switch (res.id) {
          case 100000: {
            this.additionResult = this.jsonConvert.deserialize(res, AdditionResult);
            // broadcast result
            this.comunicationService.changeAdditionResult(this.additionResult);
            this.result = this.additionResult;
            break;
          }
          case 100001: {
            this.subtractionResult = this.jsonConvert.deserialize(res, SubtractionResult);
            // broadcast result
            this.comunicationService.changeSubtractionResult(this.subtractionResult);
            this.result = this.subtractionResult;
            break;
          }
          case 100002: {
            this.multiplicationResult = this.jsonConvert.deserialize(res, MultiplicationResult);
            // broadcast result
            this.comunicationService.changeMultiplycationResult(this.multiplicationResult);
            this.result = this.multiplicationResult;
            break;
          }
          case 100003:
          case 100004: {
            this.divisionResult = this.jsonConvert.deserialize(res, DivisionResult);
            // broadcast result
            this.comunicationService.changeDivisionResult(this.divisionResult);
            this.result = this.divisionResult;
            break;
          }
        }
      },
      error => {
        this.alertService.error(error);
      });
  }
}
