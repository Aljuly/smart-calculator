import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AlertService } from '.';
import { Result } from '../_models';

@Injectable()
export class DataService {
  private baseUrl = 'http://192.168.99.100:8080/smart-calculator';
  constructor(private httpClient: HttpClient) {
  }

  get_description(value: number) {
    const params = new HttpParams().set('id', String(value));
    return this.httpClient.request('POST', this.baseUrl + '/general/description', {responseType: 'json', params});
  }

  get_result(id: number, firstNumber: string, secondNumber: string) {
    const params = new HttpParams()
    .set('id', String(id)).set('firstnumber', firstNumber)
    .set('secondnumber', secondNumber);
    return this.httpClient.request<any>('GET', this.baseUrl + '/calculations/calculate', {responseType: 'json', params})
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: string) {
    // The backend returned an unsuccessful response code.
    // The response body may contain clues as to what went wrong,
    // console.error(`Backend returned error: ` + error);
    // this.alertService.error(error);
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened. ' + error);
  }
}

