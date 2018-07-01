import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user/user';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AppConfig } from '../config';
import { AuthService } from "../auth/auth.service";
import { MoneyAndAmount } from "../publication/publication";


@Injectable({
  providedIn: 'root'
})
export class CreditService {

  constructor(authService: AuthService, private http: HttpClient, private config: AppConfig) { }


  getUser(): Observable<User> {
    return this.http.post<User>(this.config.serveUrl + 'user/currentUser/', {});
  }

  charge(money: MoneyAndAmount) {
    return this.http.post<User>(this.config.serveUrl + 'user/addMoney/', money);
  }

}
