import { Injectable } from '@angular/core';

import * as auth0 from 'auth0-js';
import { environment } from './../../environments/environment';
import { Router } from '@angular/router';

// AGREGADO PARA TENER USUARIOS
import { User } from '../user/user';
import { UserServiceService } from '../user/service/user-service.service';
// FIN AGREGADO PARA TENER USUARIOS

(window as any).global = window;

@Injectable()
export class AuthService {
  // Create Auth0 web auth instance
  auth0 = new auth0.WebAuth({
    clientID: environment.auth.clientID,
    domain: environment.auth.domain,
    responseType: 'token id_token',
    redirectUri: environment.auth.redirect,
    audience: environment.auth.audience,
    scope: environment.auth.scope
  });
  // // Store authentication data
  // expiresAt: number;
  // userProfile: any;
  // accessToken: string;
  // authenticated: boolean;

// <<<<<<< HEAD
//   constructor(private router: Router) {
//     // this.getAccessToken();
// =======
  constructor(private userService : UserServiceService  // MODIFICADO PARA TENER USUARIOS
    , private router: Router) {
    this.getToken();

  }

  login() {
    this.auth0.authorize();
  }


  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.setSession(authResult);
        this.router.navigate(['/']);
      } else if (err) {
        this.router.navigate(['/']);
        console.log(err);
      }
    });
  }

  private setSession(authResult): void {
    // Set the time that the Access Token will expire at
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());
    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', expiresAt);
    localStorage.setItem('email', authResult.idTokenPayload.email);
  }
  public logout(): void {
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    localStorage.removeItem('email');
    // Go back to the home route
    this.router.navigate(['/']);
  }


// <<<<<<< HEAD
// =======
//   private _setSession(authResult, profile) {
//     // Save authentication data and update login status subject
//     this.expiresAt = authResult.expiresIn * 1000 + Date.now();
//     this.accessToken = authResult.accessToken;
//     this.userProfile = profile;
//     this.authenticated = true;
//  // AGREGADO PARA TENER USUARIOS
//     this.userService.setAuthService(this);
//     this.userService.getAUser(this.userProfile)
// // FIN AGREGADO PARA TENER USUARIOS
//     }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // Access Token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at') || '{}');
    return new Date().getTime() < expiresAt;
  }

  public getToken() {
    return localStorage.get('access_token');
  }

}
