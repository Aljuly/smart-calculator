import { JsonObject, JsonProperty } from 'json2typescript';
/**
 * Class for storing data of the one step of the calculations
 *
 * @author Alexander Zhulinsky
 * @version 1.0 07 Aug 2018
 */
@JsonObject('Step')
export class Step {
  @JsonProperty('firstNumber', String)
  private _firstnumber: string;
  @JsonProperty('secondNumber', String)
  private _secondnumber: string;
  @JsonProperty('difference', String)
  private _difference: string;

  constructor(
    firstnumber?: string,
    secondnumber?: string,
    difference?: string) {
    this._firstnumber = firstnumber || void 0;
    this._secondnumber = secondnumber || void 0;
    this._difference = difference || void 0;
  }

  get firstnumber(): string {
    return this._firstnumber;
  }
  set firstnumber(value: string) {
    this._firstnumber = value;
  }

  get secondnumber(): string {
    return this._secondnumber;
  }
  set secondnumber(value: string) {
    this._secondnumber = value;
  }

  get difference(): string {
    return this._difference;
  }
  set difference(value: string) {
    this._difference = value;
  }

}
