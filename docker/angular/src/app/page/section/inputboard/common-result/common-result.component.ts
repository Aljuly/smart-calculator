import { Formatter } from './../../../../_helpers/formatter';
import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { Result, AdditionResult, SubtractionResult, MultiplicationResult, DivisionResult } from 'src/app/_models';
import { ComunicationService } from 'src/app/_services';

@Component({
  selector: 'app-common-result',
  templateUrl: './common-result.component.html',
  styleUrls: ['./common-result.component.css']
})
export class CommonResultComponent implements OnChanges {
  @Input() result: Result;
  private comunicationService: ComunicationService;
  output: string;
  numerator: string;
  denominator: string;
  constructor(
    private formatter: Formatter
  ) { }

  ngOnChanges() {
    // if nothing to show then exit
    if (this.result.isEmpty()) { return; }
    // reset previous values
    this.output = '';
    this.numerator = '';
    this.denominator = '';
    // fill with new values
    switch (true) {
      case (this.result instanceof AdditionResult): {
        this.output = (<AdditionResult>this.result).sum;
        break;
      }
      case (this.result instanceof SubtractionResult): {
        this.output = (<SubtractionResult>this.result).difference;
        if ((<SubtractionResult>this.result).negative) {
          this.output = '-' + this.output;
        }
        break;
      }
      case (this.result instanceof MultiplicationResult): {
        this.output = (<MultiplicationResult>this.result).product;
        break;
      }
      case (this.result instanceof DivisionResult): {
        if (this.result.id === 100003) {
          this.output = (<DivisionResult>this.result).quotient;
          this.numerator = (<DivisionResult>this.result).reminder;
          this.denominator = (<DivisionResult>this.result).divisor;
        }
        if (this.result.id === 100004) {
          this.output = (<DivisionResult>this.result).quotient;
          this.output = this.output + this.formatter.formatFraction(
            (<DivisionResult>this.result).fraction,
            Number((<DivisionResult>this.result).divisor)
          );
        }
        break;
      }
    }
  }
}

