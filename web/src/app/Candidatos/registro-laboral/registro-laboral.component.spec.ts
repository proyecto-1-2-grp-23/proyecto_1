import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroLaboralComponent } from './registro-laboral.component';

describe('RegistroLaboralComponent', () => {
  let component: RegistroLaboralComponent;
  let fixture: ComponentFixture<RegistroLaboralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistroLaboralComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistroLaboralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
