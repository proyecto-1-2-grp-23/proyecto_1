import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaCandidatosEquipoComponent } from './lista-candidatos-equipo.component';

describe('ListaCandidatosEquipoComponent', () => {
  let component: ListaCandidatosEquipoComponent;
  let fixture: ComponentFixture<ListaCandidatosEquipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaCandidatosEquipoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaCandidatosEquipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
