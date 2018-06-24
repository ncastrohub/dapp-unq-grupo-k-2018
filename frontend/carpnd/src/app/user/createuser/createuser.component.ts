import { Component, OnInit, Input } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../service/user-service.service';
import { Router } from '@angular/router';
import { Subscription }   from 'rxjs';

import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.css']
})
export class CreateuserComponent {

  constructor(private service: UserServiceService, private router: Router) { }
  
  user:User;
  subscription: Subscription;

  ngOnInit() {
    this.subscription = this.service.userObservable.subscribe(user => this.user = user);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  errorList = [];

  onSubmit() {
    this.service.updateUser(this.user).subscribe(
        data => {
          this.router.navigate(['/']);
        },
        error => this.errorList.push(error)
      );
    }

}
