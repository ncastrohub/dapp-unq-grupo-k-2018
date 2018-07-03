import { Component, OnInit } from '@angular/core';
import { User } from '../../user/user';
import { MoneyAndAmount } from '../../publication/publication';
import { UserServiceService } from '../../user/service/user-service.service';
import { Router } from '@angular/router';
import { CreditService } from '../credit.service'; 


@Component({
  selector: 'app-credit',
  templateUrl: './credit.component.html',
  styleUrls: ['./credit.component.css']
})


export class CreditComponent implements OnInit {

  money: MoneyAndAmount;
  errorList = [];
  user: User;
  loading = true;
  constructor(private userService: UserServiceService, private creditService: CreditService, private router: Router) { 
     this.money = new MoneyAndAmount();
     this.money.currency = "ARS";
  }


   ngOnInit() {
  	this.userService.getUser().subscribe(
      user => {
        this.loading = false;
        this.user = user;
      }
    );
  }

   onSubmit() {
    this.loading = true;
    this.creditService.charge(this.money).subscribe(
        data => {
          this.router.navigate(['/']);
        },
        error => this.errorList.push(error)
      );
    }

}
