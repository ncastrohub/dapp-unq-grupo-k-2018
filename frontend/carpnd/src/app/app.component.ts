import { Component } from '@angular/core';
import { } from '@types/googlemaps';
import { AuthService } from './auth/auth.service';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Lend your Car';
  constructor(public authService: AuthService, private translate: TranslateService) {

  	translate.setDefaultLang('en');
  	translate.use('es');
  }

  useLanguage(language: string) {
    this.translate.use(language);
	}
}
