import { Component, Input, OnChanges } from '@angular/core';
import { MultiplicationResult } from '../../../../_models';

@Component({
  selector: 'app-multiplycation-formatter',
  templateUrl: './multiplycation-formatter.component.html',
  styleUrls: ['./multiplycation-formatter.component.css']
})
export class MultiplycationFormatterComponent implements OnChanges {
  @Input() multiplicationResult: MultiplicationResult;
  firstFactor: string[];
  secondFactor: string[];
  product: string[];
  stub: string[];
  stepsOut: any[];

  constructor() { }

  ngOnChanges() {
    if (this.multiplicationResult.isEmpty()) { return; }
    // some kind of index magik
    this.stepsOut = new Array();
    const m = Math.max(
      this.multiplicationResult.firstFactor.length,
      this.multiplicationResult.secondFactor.length) + 1;
    const f = m - this.multiplicationResult.firstFactor.length;
    const s = m - this.multiplicationResult.secondFactor.length;
    let z = this.multiplicationResult.product.length - m + 1;
    let r = 0;
    if (z < 0) {
      r = - z;
      z = 0;
    }
    this.stub = Array.from(this.assemblyString(z, ' '));
    this.firstFactor = Array.from(this.assemblyString(f, ' ').concat(this.multiplicationResult.firstFactor));
    this.secondFactor = Array.from(this.assemblyString(s, ' ').concat(this.multiplicationResult.secondFactor));
    this.product = Array.from(this.assemblyString(r, ' ').concat(this.multiplicationResult.product));
    for (let k = 0; k < this.multiplicationResult.steps.length; k++) {
      if (this.multiplicationResult.steps[k] !== '0') {
        const l = m + z - (this.multiplicationResult.steps[k].length + k);
        this.stepsOut.push(Array.from(this.assemblyString(l, ' ').concat(this.multiplicationResult.steps[k],
          this.assemblyString(k, ' '))));
      }
    }
  }
  // Utility function that forms string from given char of given length
  private assemblyString(numberOfSymbols: number, symbol: string) {
    if (numberOfSymbols === 0) { return ''; }
    return new Array(numberOfSymbols).join(symbol);
  }
}
