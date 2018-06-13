import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CostDefinerComponent } from './cost-definer.component';

describe('CostDefinerComponent', () => {
  let component: CostDefinerComponent;
  let fixture: ComponentFixture<CostDefinerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CostDefinerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CostDefinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
