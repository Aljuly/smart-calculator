import { Component, OnInit, OnDestroy } from '@angular/core';
import { AdditionResult, MultiplicationResult, DivisionResult, Description, SubtractionResult } from '../../../_models';
import { ComunicationService } from '../../../_services';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  description: Description;
  id: number;
  additionResult: AdditionResult;
  subtractionResult: SubtractionResult;
  multiplicationResult: MultiplicationResult;
  divisionResult: DivisionResult;
  constructor(private comunicationService: ComunicationService) { }
  // Subscribe on changes
  ngOnInit(): void {
    this.comunicationService.DescriptionMessage
    .subscribe((res) => {
      this.id = res.id;
    });
    this.comunicationService.additionResultMessage
    .subscribe(additionResult => this.additionResult = additionResult);
    this.comunicationService.subtractionResultMessage
    .subscribe(subtractionResult => this.subtractionResult = subtractionResult);
    this.comunicationService.multiplicationResultMessage
    .subscribe(multiplicationResult => this.multiplicationResult = multiplicationResult);
    this.comunicationService.divisionResultMessage
    .subscribe(divisionResult => this.divisionResult = divisionResult);
  }
}
