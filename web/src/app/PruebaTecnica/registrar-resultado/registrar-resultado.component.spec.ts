import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarResultadoComponent } from './registrar-resultado.component';

describe('RegistrarResultadoComponent', () => {
  let component: RegistrarResultadoComponent;
  let fixture: ComponentFixture<RegistrarResultadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarResultadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarResultadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
