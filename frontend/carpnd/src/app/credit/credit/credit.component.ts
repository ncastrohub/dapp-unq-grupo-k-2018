import { Component, OnInit } from '@angular/core';
import { User } from '../../user/user';
import { MoneyAndAmount } from '../../publication/publication';
import { UserServiceService } from '../../user/service/user-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-credit',
  templateUrl: './credit.component.html',
  styleUrls: ['./credit.component.css']
})

export class CreditComponent implements OnInit {

  constructor(private service: UserServiceService, private router: Router) { }

  user: User;

   ngOnInit() {
  	this.service.getUser().subscribe(
      user => {
        this.user = user
      }
    );
  }

}
