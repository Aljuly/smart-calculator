import { JsonObject, JsonProperty } from 'json2typescript';
import { Result } from './result';
/**
 * Class that strores info about addition operation result
 * @author Alexander Zhulinsky
 * @version 1.0 13 Aug 2018
 */
@JsonObject('AdditionResult')
export class AdditionResult extends Result {
    @JsonProperty('firstTerm', String)
    private _firstTerm: string;
    @JsonProperty('secondTerm', String, )
    private _secondTerm: string;
    @JsonProperty('sum', String, )
    private _sum: string;
    private _operationType: boolean;
    // The constructor
    constructor() {
        super();
        this._firstTerm = void 0;
        this._secondTerm = void 0;
        this._sum = void 0;
        this._operationType = false;
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
    public get sum(): string {
        return this._sum;
    }
    public set sum(value: string) {
        this._sum = value;
    }
    // Set and Get data about contained operation data
    public setAddition() {
        this._operationType = false;
    }
    public setSubtraction() {
        this._operationType = true;
    }
    public isAddition(): boolean {
        return !this._operationType;
    }
    public isSubtraction(): boolean {
        return this._operationType;
    }
    // Define whereas object is empty
    public isEmpty(): boolean {
        return (!this._firstTerm || !this._secondTerm);
    }
}
