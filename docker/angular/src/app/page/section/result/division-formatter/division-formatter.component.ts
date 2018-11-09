import { Step, DivisionResult } from '../../../../_models';
import { Component, Input, OnChanges } from '@angular/core';
import { Formatter } from 'src/app/_helpers';

@Component({
  selector: 'app-division-formatter',
  templateUrl: './division-formatter.component.html',
  styleUrls: ['./division-formatter.component.css']
})
export class DivisionFormatterComponent implements OnChanges {
  @Input() divisionResult: DivisionResult;
  dividend: string[];
  divisor: string[];
  second: string[];
  stepsOut: any[];
  result: string[];
  constructor(private formatter: Formatter) {}

  ngOnChanges() {
    if (this.divisionResult.isEmpty()) {
      console.log('Im Empty!!');
      return;
    }
    let j: number;
    let diff: number;
    let i = 1;
    // lets make a first step
    this.dividend = Array.from(this.divisionResult.dividend);
    this.divisor = Array.from(this.divisionResult.divisor);
    let firstNumber = this.divisionResult.steps[0].firstnumber;
    let secondNumber = this.divisionResult.steps[0].secondnumber;
    // identations variables
    let m: number;
    let f: number;
    let s: number;
    // assembly strings to output
    this.second = Array.from(this.formatter.assemblyString(firstNumber.length - secondNumber.length, ' ')
      .concat(this.divisionResult.steps[0].secondnumber)
      .concat(this.formatter.assemblyString(this.dividend.length - firstNumber.length, ' ')));
    this.result = Array.from(this.divisionResult.quotient.concat(this.formatter.formatFraction(this.divisionResult.fraction,
      Number(this.divisionResult.divisor))));
    let difference: string = this.divisionResult.steps[0].difference;
    j = firstNumber.length;
    // define difference for the next step
    diff = Number(difference);
    this.stepsOut = new Array();
    for (let k = 1; k < this.divisionResult.steps.length; k++) {
      i++;
      if (i === this.divisionResult.steps.length) {
        firstNumber = this.removeLeadingZero(this.divisionResult.steps[k].firstnumber);
        j += firstNumber.length - String(diff).length;
        if (difference.length === 0) { j++; }
        const stepOut = {
          firstnumber: Array.from(this.formatter.assemblyString(j - firstNumber.length + 1, ' ').concat(firstNumber)),
          secondnumber: Array.from(''),
          stub: Array.from('')
        };
        this.stepsOut.push(stepOut);
      } else {
        firstNumber = this.removeLeadingZero(this.divisionResult.steps[k].firstnumber);
        secondNumber = this.divisionResult.steps[k].secondnumber;
        difference = this.divisionResult.steps[k].difference;
        // define identation on the current step
        m = Math.max(firstNumber.length, secondNumber.length);
        f = m - firstNumber.length;
        s = m - secondNumber.length;
        // increase indent on count of added digits
        j += firstNumber.length - String(diff).length;
        // if the difference is 0 than omit it
        if (diff === 0) { j++; }
        // define the difference for the next step
        diff = Number(difference);
        // one step to output
        const stepOut = {
          firstnumber: Array.from(this.formatter.assemblyString(f, ' ').concat(firstNumber)),
          secondnumber: Array.from(this.formatter.assemblyString(s, ' ').concat(secondNumber)),
          stub: Array.from(this.formatter.assemblyString(j - m, ' '))
        };
        this.stepsOut.push(stepOut);
      }
    }
  }
  // Utility function for removing leading zero (if exists)
  private removeLeadingZero(str: string) {
    if (str.length > 1 && str.charAt(0) === '0') {
      str = ' ' + str.substring(1);
    }
    return str;
  }
}
