import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrevistasProgramadasComponent } from './entrevistas-programadas.component';

describe('EntrevistasProgramadasComponent', () => {
  let component: EntrevistasProgramadasComponent;
  let fixture: ComponentFixture<EntrevistasProgramadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntrevistasProgramadasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrevistasProgramadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
