import { Component, ViewEncapsulation, OnInit, ViewChild, ElementRef } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-callback',
  template: `
   <div class="text-center">
        <i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
        <div>Working...</div>
    </div>
  `,
  styles: []
})
export class CallbackComponent implements OnInit {

   closeResult: string;

 
  constructor(private authService: AuthService, private route: ActivatedRoute) {}
  ngOnInit() {

    let authorization_role = this.route.snapshot.paramMap.get('authorization_role');
    if (authorization_role == 'login') {
      this.authService.handleAuthenticationLogin();
    }
    if (authorization_role == 'sign_up') {
      this.authService.handleAuthenticationSignUp();
    }
  }

}
