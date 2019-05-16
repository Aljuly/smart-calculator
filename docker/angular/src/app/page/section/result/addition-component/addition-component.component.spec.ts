import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionComponent } from './addition-component.component';

describe('AdditionComponentComponent', () => {
  let component: AdditionComponent;
  let fixture: ComponentFixture<AdditionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdditionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
