import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarCandidatoProyectoComponent } from './agregar-candidato-proyecto.component';

describe('AgregarCandidatoProyectoComponent', () => {
  let component: AgregarCandidatoProyectoComponent;
  let fixture: ComponentFixture<AgregarCandidatoProyectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgregarCandidatoProyectoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarCandidatoProyectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
