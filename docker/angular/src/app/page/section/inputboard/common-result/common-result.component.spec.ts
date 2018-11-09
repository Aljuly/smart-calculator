import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonResultComponent } from './common-result.component';

describe('CommonResultComponent', () => {
  let component: CommonResultComponent;
  let fixture: ComponentFixture<CommonResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
