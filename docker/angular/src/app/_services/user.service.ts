import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  getById(id: number) {
    return this.http.get(`/users/` + id);
  }

  register(user: User) {
    return this.http.post(`http://192.168.99.100:8080/smart-calculator/users/register`, user);
  }

  update(user: User) {
    return this.http.put(`/users`, user);
  }

  delete(id: number) {
    return this.http.delete(`/users/` + id);
  }
}
