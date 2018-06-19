import {Component, Output, EventEmitter, Input, OnInit} from '@angular/core';
import {NgbDatepickerConfig, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import { PublicationService } from '../publication.service';
import { Subscription }   from 'rxjs';

@Component({
  selector: 'ngbd-datepicker-config',
  templateUrl: './date-selector.component.html',
  providers: [NgbDatepickerConfig] // add NgbDatepickerConfig to the component providers
})

export class DateSelectorComponent {
  dateList = []; 
  disabledDates: number[][];
  model;

  @Output() public onComplete: EventEmitter<any> = new EventEmitter();
  subscription: Subscription;

  constructor(config: NgbDatepickerConfig, service: PublicationService) {
    
      this.subscription = service.datesList$.subscribe(
        data => {
          config.markDisabled = (date: NgbDateStruct) => {
            this.disabledDates = data;
            const d = new Date(date.year, date.month - 1, date.day);
            const rareDate = [date.year,date.month,date.day];
            return this.disabledDates.indexOf(rareDate) > -1;
          };
        });
      // return d.getDay() === 0 || d.getDay() === 6;
    let today = new Date()
    config.minDate = {year: today.getFullYear(), month: today.getMonth() + 1, day: today.getDate()};
    config.maxDate = {year: 2099, month: 12, day: 31};
    // days that don't belong to current month are not visible
    config.outsideDays = 'hidden';


    // customize default values of datepickers used by this component tree
  }

  addDay(){
    let year  = this.model.year;
    let month = this.model.month;
    let day = this.model.day;
    this.dateList.push([year, month, day]);
  }

  complete() {
    this.onComplete.emit({ event:event, dateList: this.dateList });
  }
}