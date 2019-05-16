import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { JsonConvert } from 'json2typescript';
import { User } from '../_models';

@Injectable()
export class AuthenticationService {
  jsonConvert: JsonConvert;
  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post<any>(`http://192.168.99.100:8080/smart-calculator/users/login`, { username: username, password: password })
      .pipe(map(res => {
        // Check the detailed reference in the chapter "JsonConvert class properties and methods"
        this.jsonConvert = new JsonConvert();
        // this.jsonConvert.operationMode = OperationMode.LOGGING; // print some debug data
        this.jsonConvert.ignorePrimitiveChecks = false; // don't allow assigning number to string etc.
        // json converter returns an Array!
        console.log(JSON.stringify(res));
        const user = this.jsonConvert.deserialize(res, User);
        // const user = res;
        console.log(user.username + ' is authenticated!');
        // login successful if there's a jwt token in the response
        if (user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
        }
        return user;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
