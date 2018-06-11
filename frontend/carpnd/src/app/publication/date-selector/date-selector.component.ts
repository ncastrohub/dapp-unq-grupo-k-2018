import {Component, Output, EventEmitter} from '@angular/core';
import {NgbDatepickerConfig, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-datepicker-config',
  templateUrl: './date-selector.component.html',
  providers: [NgbDatepickerConfig] // add NgbDatepickerConfig to the component providers
})

export class DateSelectorComponent {
  dateList = []; 
  
  model;

  @Output() public onComplete: EventEmitter<any> = new EventEmitter();
  
  constructor(config: NgbDatepickerConfig) {
    // customize default values of datepickers used by this component tree
    config.minDate = {year: 1900, month: 1, day: 1};
    config.maxDate = {year: 2099, month: 12, day: 31};

    // days that don't belong to current month are not visible
    config.outsideDays = 'hidden';

    // weekends are disabled
    config.markDisabled = (date: NgbDateStruct) => {
      const d = new Date(date.year, date.month - 1, date.day);
      return d.getDay() === 0 || d.getDay() === 6;
    };
  }

  addDay(){
    let year  = this.model.year;
    let month = this.model.month;
    let day = this.model.day;
    this.dateList.push([year, month, day]);
  }

  onSubmit() {
    this.onComplete.emit({ event:event, dateList: this.dateList });
  }
}