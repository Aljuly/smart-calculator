import { Component, OnInit, Input, OnChanges, ChangeDetectorRef } from '@angular/core';
import { SubtractionResult } from '../../../../_models';

@Component({
  selector: 'app-subtraction-formatter',
  templateUrl: './subtraction-formatter.component.html',
  styleUrls: ['./subtraction-formatter.component.css']
})
export class SubtractionFormatterComponent implements OnChanges {
  @Input() subtractionResult: SubtractionResult;
  firstTerm: string[];
  secondTerm: string[];
  difference: string[];
  stub: string[];
  constructor() { }

  ngOnChanges() {
    if (this.subtractionResult.isEmpty()) { return; }
    const m = Math.max(
      this.subtractionResult.firstTerm.length,
      this.subtractionResult.secondTerm.length);
    const f = m - this.subtractionResult.firstTerm.length;
    const s = m - this.subtractionResult.secondTerm.length;
    let z = this.subtractionResult.difference.length - m - 1;
    let r = 0;
    if (z < 0) {
      r = -z;
      z = 0;
    }
    this.stub = Array.from(this.assemblyString(z, ' '));
    this.firstTerm = Array.from(this.assemblyString(f, ' ').concat(this.subtractionResult.firstTerm));
    this.secondTerm = Array.from(this.assemblyString(s, ' ').concat(this.subtractionResult.secondTerm));
    this.difference = Array.from(this.assemblyString(r, ' ').concat(this.subtractionResult.difference));
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
