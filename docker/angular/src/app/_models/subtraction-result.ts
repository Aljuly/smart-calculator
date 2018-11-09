import { Result } from './result';
import { JsonObject, JsonProperty } from 'json2typescript';

/**
 * Class that strores info about subtraction operation result
 * @author Alexander Zhulinsky
 * @version 1.0 29 Oct 2018
 */
@JsonObject('SubtractionResult')
export class SubtractionResult extends Result {
    @JsonProperty('menued', String)
    private _firstTerm: string;
    @JsonProperty('subtrahent', String, )
    private _secondTerm: string;
    @JsonProperty('difference', String, )
    private _difference: string;
    @JsonProperty('negative', Boolean)
    private _negative: boolean;
    // The constructor
    constructor() {
        super();
        this._firstTerm = void 0;
        this._secondTerm = void 0;
        this._difference = void 0;
        this._negative = false;
    }
    // Getters and Settres
    public get firstTerm(): string {
        return this._firstTerm;
    }
    public set firstTerm(value: string) {
        this._firstTerm = value;
    }
    public get secondTerm(): string {
        return this._secondTerm;
    }
    public set secondTerm(value: string) {
        this._secondTerm = value;
    }
    public get difference(): string {
        return this._difference;
    }
    public set difference(value: string) {
        this._difference = value;
    }
    public get negative(): boolean {
        return this._negative;
    }
    public set negative(value: boolean) {
        this._negative = value;
    }
    // Define whereas object is empty
    public isEmpty(): boolean {
        return (!this._firstTerm || !this._secondTerm);
    }
}
