import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsernotexistsComponent } from './usernotexists.component';

describe('UsernotexistsComponent', () => {
  let component: UsernotexistsComponent;
  let fixture: ComponentFixture<UsernotexistsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsernotexistsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsernotexistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
