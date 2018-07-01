import { Component, Output, EventEmitter, Input, ViewChild} from '@angular/core';
import { NgbDatepickerConfig} from '@ng-bootstrap/ng-bootstrap';
import { PublicationService } from '../publication.service';
import { Subscription }   from 'rxjs';
// NgbDateStruct
@Component({
  selector: 'ngbd-datepicker-config',
  templateUrl: './date-selector.component.html',
  providers: [NgbDatepickerConfig]
})

export class DateSelectorComponent {
  dateList = []; 
  model;

  @Input() callback: Function;

  // @ViewChild('myname') input; 
  @Output() public onComplete: EventEmitter<any> = new EventEmitter();
  subscription: Subscription;

  constructor(config: NgbDatepickerConfig, service: PublicationService) {
    let today = new Date()
    config.minDate = {year: today.getFullYear(), month: today.getMonth() + 1, day: today.getDate()};
    config.maxDate = {year: 2099, month: 12, day: 31};
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