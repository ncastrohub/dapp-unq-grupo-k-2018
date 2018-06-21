import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { Observable, of,  BehaviorSubject } from 'rxjs';
import { AppConfig } from '../../config';

@Injectable()
export class UserServiceService {

  private userSource = new BehaviorSubject<User>(new User);
  userObservable = this.userSource.asObservable();

  constructor(private http: HttpClient, private config: AppConfig) { }

  getUser(user : User): Observable<User> {
    user.lastName = "mocked";
    if (!user.cuil) { user.cuil = "-emptyCUIL-"}

    return this.http.post<User>(this.config.serveUrl + 'publication/user/getByEmail/', user);
  }

  getUserList(): Observable<[User]> {
  	return this.http.get<[User]>( this.config.serveUrl + 'publication/user/list');
  }

  createUser(user:User): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/user/new', user);
  }

  deleteUser(user: User): Observable<any> {
  	return this.http.post<any>(this.config.serveUrl + 'publication/user/delete', user);
  }

  updateUser(user: User): Observable<any> {
    return this.http.post<any>( this.config.serveUrl + 'publication/user/edit', user);
  }

  loadUserToEdit(user: User) {
    this.userSource.next(user);
  }

}
