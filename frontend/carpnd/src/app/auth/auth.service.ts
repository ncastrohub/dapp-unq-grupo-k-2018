import { Injectable } from '@angular/core';
import * as auth0 from 'auth0-js';
import { environment, environmenttwo } from './../../environments/environment';
import { Router } from '@angular/router';
import { User } from '../user/user';
import { UserServiceService } from '../user/service/user-service.service';

(window as any).global = window;

@Injectable()
export class AuthService {

      auth0: any;

      constructor(private userService : UserServiceService
        , private router: Router) {
      }

      login() {
        this.auth0 = new auth0.WebAuth(environment.auth);
        this.auth0.authorize();
      }

      signUp() {
        this.auth0 = new auth0.WebAuth(environmenttwo.auth);
        this.auth0.authorize();
      }

      public handleAuthenticationLogin(): void {
        this.auth0 = new auth0.WebAuth(environment.auth);
        this.auth0.parseHash((err, authResult) => {
          if (authResult && authResult.accessToken && authResult.idToken) {
            window.location.hash = '';
            this.getUserInfo(authResult);
          } else if (err) {
            this.router.navigate(['/']);
            console.log(err);
          }
        });
      } 

      public handleAuthenticationSignUp(): void {
        this.auth0 = new auth0.WebAuth(environmenttwo.auth);
        this.auth0.parseHash((err, authResult) => {
          if (authResult && authResult.accessToken && authResult.idToken) {
            window.location.hash = '';
            this.createNewProfile(authResult);
          } else if (err) {
            this.router.navigate(['/']);
            console.log(err);
          }
        });
      }

      private _setSession(authResult, profile): void {
        localStorage.setItem('access_token', authResult.accessToken);
        let response = this.userService.getUser()
         response.subscribe(
          data => {
            let expire_at = authResult.expiresIn * 1000 + Date.now();
            localStorage.setItem('id_token', authResult.idToken);
            localStorage.setItem('expires_at', expire_at.toString());
            localStorage.setItem('email', authResult.idTokenPayload.email);
            this.router.navigate(['/']);
          },
          error => {
            localStorage.removeItem('access_token');
            this.router.navigate(['/user-not-exists']);
          }
        )
      }

      private setSessionData(authResult, profile): void {
        localStorage.setItem('access_token', authResult.accessToken);
        let response = this.userService.createNewUserFromProfile(profile);
         response.subscribe(
          data => {
            let expire_at = authResult.expiresIn * 1000 + Date.now();
            localStorage.setItem('access_token', authResult.accessToken);
            localStorage.setItem('id_token', authResult.idToken);
            localStorage.setItem('expires_at', expire_at.toString());
            localStorage.setItem('email', authResult.idTokenPayload.email);
            this.router.navigate(['/user/detail']);
          },
          error => {
            localStorage.removeItem('access_token');
            this.router.navigate(['/']);

          }
        )
      }


  
      public logout(): void {
        this.auth0 = new auth0.WebAuth(environment.auth);
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

      }

      createNewProfile(authResult) {
        // Use access token to retrieve user's profile and set session
        this.auth0.client.userInfo(authResult.accessToken, (err, profile) => {
          if (profile) {
            this.setSessionData(authResult, profile)
          }
        });

      }
}