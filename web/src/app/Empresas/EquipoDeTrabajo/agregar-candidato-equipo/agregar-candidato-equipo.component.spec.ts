import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarCandidatoEquipoComponent } from './agregar-candidato-equipo.component';

describe('AgregarCandidatoEquipoComponent', () => {
  let component: AgregarCandidatoEquipoComponent;
  let fixture: ComponentFixture<AgregarCandidatoEquipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgregarCandidatoEquipoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarCandidatoEquipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
