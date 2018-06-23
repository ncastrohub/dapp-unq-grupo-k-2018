import { Injectable } from '@angular/core';
import * as auth0 from 'auth0-js';
import { environment } from './../../environments/environment';
import { Router } from '@angular/router';
import { User } from '../user/user';
import { UserServiceService } from '../user/service/user-service.service';

(window as any).global = window;

@Injectable()
export class AuthService {
  auth0 = new auth0.WebAuth({
    clientID: environment.auth.clientID,
    domain: environment.auth.domain,
    responseType: 'token id_token',
    redirectUri: environment.auth.redirect,
    audience: environment.auth.audience,
    scope: environment.auth.scope
  });
  
  constructor(private userService : UserServiceService
    , private router: Router) {
  }

  login() {
    this.auth0.authorize();
  }

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.getUserInfo(authResult);
        this.router.navigate(['/']);
      } else if (err) {
        this.router.navigate(['/']);
        console.log(err);
      }
    });
  }

  private _setSession(authResult, profile): void {
    let response = this.userService.getAUser(profile)
     response.subscribe(
      data => {
        localStorage.setItem('access_token', authResult.accessToken);
        localStorage.setItem('id_token', authResult.idToken);
        localStorage.setItem('expires_at', authResult.expiresIn * 1000 + Date.now());
        localStorage.setItem('email', authResult.idTokenPayload.email);
        this.router.navigate(['/publication/list']);
      },
      error => {
        let error = error;
        this.router.navigate(['/']);
      }
    );
  }
  
  public logout(): void {
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    localStorage.removeItem('email');
    this.auth0.logout({
      returnTo: 'http://localhost:4200',
      clientID: environment.auth.clientID
    });
  }

  public isAuthenticated(): boolean {
    const expiresAt = JSON.parse(localStorage.getItem('expires_at') || '{}');
    return new Date().getTime() < expiresAt;
  }

  public getToken() {
    return localStorage.getItem('access_token');
  }

  getUserInfo(authResult) {
    // Use access token to retrieve user's profile and set session
    this.auth0.client.userInfo(authResult.accessToken, (err, profile) => {
      if (profile) {
        this._setSession(authResult, profile);
      }
  });


// private _setSession(authResult, profile) {
// -    // Save authentication data and update login status subject
// -    this.expiresAt = authResult.expiresIn * 1000 + Date.now();
// -    this.accessToken = authResult.accessToken;
// -    this.userProfile = profile;
// -    this.authenticated = true;
// - // AGREGADO PARA TENER USUARIOS
// -    this.userService.setAuthService(this);
// -    this.userService.getAUser(this.userProfile)
// -// FIN AGREGADO PARA TENER USUARIOS
// -    }
// -
// -  logout() {
// -    // Log out of Auth0 session
// -    // Ensure that returnTo URL is specified in Auth0
// -    // Application settings for Allowed Logout URLs
// -    this.auth0.logout({
// -      returnTo: 'http://localhost:4200',
// -      clientID: environment.auth.clientID
// -    });

}
