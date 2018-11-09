import { Step } from './step';
import { JsonObject, JsonProperty } from 'json2typescript';
import { Result } from './result';

/**
 * Class that strores info about division result
 * @author Alexander Zhulinsky
 * @version 1.0 13 Aug 2018
 */
@JsonObject('DivisionResult')
export class DivisionResult extends Result {
    @JsonProperty('dividend', String)
    private _dividend: string;
    @JsonProperty('divisor', String)
    private _divisor: string;
    @JsonProperty('quotient', String)
    private _quotient: string;
    @JsonProperty('reminder', String)
    private _reminder: string;
    @JsonProperty('alert', String)
    private _alert: string;
    @JsonProperty('fraction', String)
    private _fraction: string;
    @JsonProperty('steps', [Step])
    private _steps: Step[];
    // The constructor
    constructor() {
        super();
        this._dividend = void 0;
        this._divisor = void 0;
        this._dividend = void 0;
        this._quotient = void 0;
        this._reminder = void 0;
        this._alert = void 0;
        this._fraction = void 0;
        this._steps = void 0;
    }
    // Getters and Setters
    public set dividend(value: string) {
        this._dividend = value;
    }
    public get dividend(): string {
        return this._dividend;
    }
    public get divisor(): string {
        return this._divisor;
    }
    public set divisor(value: string) {
        this._divisor = value;
    }
    public get quotient(): string {
        return this._quotient;
    }
    public set quotient(value: string) {
        this._quotient = value;
    }
    public get reminder(): string {
        return this._reminder;
    }
    public set reminder(value: string) {
        this._reminder = value;
    }
    public get alert(): string {
        return this._alert;
    }
    public set alert(value: string) {
        this._alert = value;
    }
    public get fraction(): string {
        return this._fraction;
    }
    public set fraction(value: string) {
        this._fraction = value;
    }
    public get steps(): Step[] {
        return this._steps;
    }
    public set steps(value: Step[]) {
        this._steps = value;
    }
    // Define whereas object is empty
    public isEmpty(): boolean {
        return (!this._dividend || !this._divisor);
    }
}
