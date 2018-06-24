import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AppConfig } from '../../config';
import { AuthService } from "../../auth/auth.service";

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

  getAUser(profile: any) {
    let user = new User();
    user.name     = profile.given_name;
    user.email    = profile.email;
    user.lastName = profile.family_name;
    user.cuil     = "";
    return this.getUser(user)
  }

  getUser(user : User): Observable<User> {
    return this.http.post<User>(this.config.serveUrl + 'user/get-by-email/', user);
  }

  getUserList(): Observable<[User]> {
  	return this.http.get<[User]>( this.config.serveUrl + 'publication/user/list');
  }

  createUser(user:User): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + '/user/new', user);
  }

  createNewUserFromProfile(profile): Observable<User> {
    let user = new User();
    user.name     = profile.given_name;
    user.email    = profile.email;
    user.lastName = profile.family_name;
    return this.createUser(user);
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
