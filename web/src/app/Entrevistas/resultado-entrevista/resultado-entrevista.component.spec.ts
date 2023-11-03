import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadoEntrevistaComponent } from './resultado-entrevista.component';

describe('ResultadoEntrevistaComponent', () => {
  let component: ResultadoEntrevistaComponent;
  let fixture: ComponentFixture<ResultadoEntrevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultadoEntrevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultadoEntrevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
