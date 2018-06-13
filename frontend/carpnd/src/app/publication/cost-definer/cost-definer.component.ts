import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MoneyAndAmount } from '../publication';

@Component({
  selector: 'app-cost-definer',
  templateUrl: './cost-definer.component.html',
  styleUrls: ['./cost-definer.component.css']
})

export class CostDefinerComponent {

  @Output() public onComplete: EventEmitter<any> = new EventEmitter();
  cost: MoneyAndAmount;

  constructor() {
    this.cost = new MoneyAndAmount();
  }
  
  currencies = ['ARS'];

  errorList = [];

  onSubmit() {
    this.onComplete.emit({ event:event, cost: this.cost });
  }

}
