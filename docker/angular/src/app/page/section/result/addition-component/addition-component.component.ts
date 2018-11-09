import { Component, OnInit, Input, OnChanges, ChangeDetectorRef } from '@angular/core';
import { AdditionResult } from '../../../../_models';

@Component({
  selector: 'app-addition-component',
  templateUrl: './addition-component.component.html',
  styleUrls: ['./addition-component.component.css']
})
export class AdditionComponent implements OnChanges {
  @Input() additionResult: AdditionResult;
  firstTerm: string[];
  secondTerm: string[];
  sum: string[];
  stub: string[];
  constructor() { }

  ngOnChanges() {
    if (this.additionResult.isEmpty()) { return; }
    const m = Math.max(
      this.additionResult.firstTerm.length,
      this.additionResult.secondTerm.length);
    const f = m - this.additionResult.firstTerm.length;
    const s = m - this.additionResult.secondTerm.length;
    let z = this.additionResult.sum.length - m - 1;
    let r = 0;
    if (z < 0) {
      r = -z;
      z = 0;
    }
    this.stub = Array.from(this.assemblyString(z, ' '));
    this.firstTerm = Array.from(this.assemblyString(f, ' ').concat(this.additionResult.firstTerm));
    this.secondTerm = Array.from(this.assemblyString(s, ' ').concat(this.additionResult.secondTerm));
    this.sum = Array.from(this.assemblyString(r, ' ').concat(this.additionResult.sum));
  }
  // Utility function that forms string from given char of given length
  private assemblyString(numberOfSymbols: number, symbol: string) {
    if (numberOfSymbols === 0) { return ''; }
    let result = '';
    for (let i = 0; i < numberOfSymbols; i++) {
        result += symbol;
    }
    return result;
  }
}
