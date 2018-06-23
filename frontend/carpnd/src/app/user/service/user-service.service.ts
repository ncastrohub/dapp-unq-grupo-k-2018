///<reference path="../user.ts"/>
///<reference path="../../../../node_modules/rxjs/internal/Observable.d.ts"/>
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AppConfig } from '../../config';
import {AuthService} from "../../auth/auth.service";

@Injectable()
export class UserServiceService {

  user: User;
  authService: AuthService;

  setAuthService(authService: AuthService) {
    this.authService = authService;
  }

  getLoggedUser() {
    return this.user;
  }

  private userSource = new BehaviorSubject<User>(new User);
  userObservable = this.userSource.asObservable();

  constructor(private http: HttpClient, private config: AppConfig) { }

  getAUser(profile: any): Observable<User> {
    this.user = new User();
    this.user.name     = profile.name;
    this.user.email    = profile.email;
    this.user.lastName = profile.lastName;
    this.user.cuil     = profile.cuil;

    this.getUser(this.user)
      .subscribe(user => this.user = user);

    return of(this.user);
  }

  getUser(user : User): Observable<User> {
    // NECESARIOS POR LA VALIDACION DEL FORM USER
    if (!user.name)     { user.name     = "-emptyName-";     }
    if (!user.lastName) { user.lastName = "-emptyLastname-"; }
    if (!user.cuil)     { user.cuil     = "-emptyCUIL-";     }

    return this.http.post<User>(this.config.serveUrl + 'publication/user/getByEmail/', user);
  }

  getUserList(): Observable<[User]> {
  	return this.http.get<[User]>( this.config.serveUrl + 'publication/user/list');
  }

  createUser(user:User): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/user/new', user);
  }

  deleteUser(userId: number): Observable<any> {
  	return this.http.get<any>(this.config.serveUrl + 'publication/user/delete/' + userId)
      .pipe(
        tap(_ => this.authService.logout() )
      );
  }

  updateUser(user: User): Observable<any> {
    return this.http.post<any>( this.config.serveUrl + 'publication/user/edit', user);
  }

  loadUserToEdit(user: User) {
    this.userSource.next(user);
  }

}
