import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubtractionFormatterComponent } from './subtraction-formatter.component';

describe('SubtractionFormatterComponent', () => {
  let component: SubtractionFormatterComponent;
  let fixture: ComponentFixture<SubtractionFormatterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubtractionFormatterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubtractionFormatterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
