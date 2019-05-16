import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiplycationFormatterComponent } from './multiplycation-formatter.component';

describe('MultiplycationFormatterComponent', () => {
  let component: MultiplycationFormatterComponent;
  let fixture: ComponentFixture<MultiplycationFormatterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MultiplycationFormatterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiplycationFormatterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
