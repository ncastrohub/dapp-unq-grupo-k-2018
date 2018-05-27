import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../service/user-service.service';
import { Router } from '@angular/router';
import { Subscription }   from 'rxjs/Subscription';

@Component({
  selector: 'app-useredit',
  templateUrl: '../createuser/createuser.component.html',
  styleUrls: ['../createuser/createuser.component.css']
})

export class UsereditComponent implements OnInit {

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
          this.router.navigate(['/user/list']);
        },
        error => this.errorList.push(error)
      );
    }

}
