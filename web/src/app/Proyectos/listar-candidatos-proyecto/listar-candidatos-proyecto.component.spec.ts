import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCandidatosProyectoComponent } from './listar-candidatos-proyecto.component';

describe('ListarCandidatosProyectoComponent', () => {
  let component: ListarCandidatosProyectoComponent;
  let fixture: ComponentFixture<ListarCandidatosProyectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarCandidatosProyectoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarCandidatosProyectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
