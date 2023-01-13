import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaLoginComponent } from './area-login.component';

describe('AreaLoginComponent', () => {
  let component: AreaLoginComponent;
  let fixture: ComponentFixture<AreaLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AreaLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
