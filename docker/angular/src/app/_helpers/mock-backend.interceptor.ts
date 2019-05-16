import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, mergeMap, materialize, dematerialize } from 'rxjs/operators';

@Injectable()
export class MockBackendInterceptor implements HttpInterceptor {

    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // array in local storage for registered users
        const users: any[] = JSON.parse(localStorage.getItem('users')) || [];

        // wrap in delayed observable to simulate server api call
        return of(null).pipe(mergeMap(() => {

            // mock authenticate
            if (request.url.endsWith('/users/authenticate') && request.method === 'POST') {
                // find if any user matches login credentials
                const filteredUsers = users.filter(user => {
                    return user.userName === request.body.username && user.password === request.body.password;
                });
                if (filteredUsers.length) {
                    // if login details are valid return 200 OK with user details and fake jwt token
                    const user = filteredUsers[0];
                    const body = {
                        name: user.userName,
                        login: user.login,
                        email: user.email,
                        token: 'mock-jwt-token'
                    };
                    return of(new HttpResponse({ status: 200, body: body }));
                } else {
                    // else return 400 bad request
                    return throwError({ error: { message: 'Username or password is incorrect' } });
                }
            }

            // mock register user
            if (request.url.endsWith('/users/register') && request.method === 'POST') {
                // get new user object from post body
                const newUser = request.body;

                // validation
                const duplicateUser = users.filter(user => user.username === newUser.userName).length;
                if (duplicateUser) {
                    return throwError({ error: { message: 'Username "' + newUser.username + '" is already registered!' } });
                }

                // save new user
                newUser.id = users.length + 1;
                users.push(newUser);
                localStorage.setItem('users', JSON.stringify(users));

                // respond 200 OK
                return of(new HttpResponse({ status: 200 }));
            }

            // mock operations
            if (request.url.endsWith('/calculate') && request.method === 'GET') {
                switch (Number(request.params.get('id'))) {
                    case 1: {
                        const body = {
                            id: 1,
                            firstTerm: '12525',
                            secondTerm: '16894',
                            sum: '29419'
                        };
                        return of(new HttpResponse({ status: 200, body: body }));
                    }
                    case 2: {
                        const body = {
                            id: 2,
                            firstTerm: '16894',
                            secondTerm: '12525',
                            sum: '4369'
                        };
                        return of(new HttpResponse({ status: 200, body: body }));
                    }
                    case 3: {
                        const body = {
                            id: 3,
                            firstfactor: '12345',
                            secondfactor: '324',
                            product: '3999780',
                            steps: ['49380', '24690', '37035']
                        };
                        return of(new HttpResponse({ status: 200, body: body }));
                    }
                    case 100003: {
                        const body = {
                            id: 100003,
                            dividend: '630440',
                            divisor: '610',
                            quotient: '1033',
                            reminder: '310',
                            alert: '',
                            fraction: '',
                            steps: [{firstNumber: '630', secondNumber: '610', difference: '20'},
                            {firstNumber: '2044', secondNumber: '1830', difference: '214'},
                            {firstNumber: '2140', secondNumber: '1830', difference: '310'},
                            {firstNumber: '310', secondNumber: '0', difference: '310'}]
                        };
                        return of(new HttpResponse({ status: 200, body: body }));
                    }
                    case 100004: {
                        const body = {
                            id: 100004,
                            dividend: '12',
                            divisor: '123',
                            quotient: '0',
                            reminder: '12',
                            alert: '',
                            fraction: '09756',
                            steps: [{firstNumber: '12', secondNumber: '0', difference: '12'},
                            {firstNumber: '1200', secondNumber: '1107', difference: '93'},
                            {firstNumber: '930', secondNumber: '861', difference: '69'},
                            {firstNumber: '690', secondNumber: '615', difference: '75'},
                            {firstNumber: '750', secondNumber: '738', difference: '12'},
                            {firstNumber: '12', secondNumber: '0', difference: '12'}]
                        };
                        return of(new HttpResponse({ status: 200, body: body }));
                    }
                }
            }

            // pass through any requests not handled above
            return next.handle(request);
        }))

        // call materialize and dematerialize to ensure delay
        // even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
        .pipe(materialize())
        .pipe(delay(500))
        .pipe(dematerialize());
    }
}

