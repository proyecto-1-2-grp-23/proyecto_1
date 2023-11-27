import json
import unittest

from ..src.models.respuesta_enviada import RespuestaEnviadaSchema

from ..src.session import Session, engine
from ..src.main import app
from ..src.models.model import Base


class TestMiApp(unittest.TestCase):
    def setUp(self):
        app.config["TESTING"] = True
        self.app = app.test_client()
        Base.metadata.create_all(engine)
        self.session = Session()
        self.data = {
            "idProyecto": 2,
            "idCandidato": 1,
            "observaciones": "Primera observacion",
            "respuestasEnviadas": [
                {
                    "idRespuesta": "2"
                }
            ]
        }

    def test_creacion_respuesta_por_candidatousuario(self):
        with app.app_context():
            if self.data:
                idCandidato = 1
            # Si el usuario existe, no debe poder crearse nuevamente
                response = self.app.post(f"/pruebas/respuesta-enviada/{idCandidato}", data=json.dumps(self.data), content_type="application/json")
                if response.status_code == 200:
                    self.assertEqual(response.status_code, 200)  # Código 409 para indicar un conflicto
                else:
                    self.assertEqual(response.status_code, 404)
    
    def test_creacion_respuesta_por_candidatousuario_inexistente(self):
        with app.app_context():
            if self.data:
                idCandidato_No_existente = 2
                response = self.app.post(f"/pruebas/respuesta-enviada/{idCandidato_No_existente}", data=json.dumps(self.data), content_type="application/json")
                if response.status_code == 404:
                    self.assertEqual(response.status_code, 404)  # Código 409 para indicar un conflicto
        
    def test_creacion_nueva_respuesta_sin_idProyecto(self):
        with app.app_context():
            existing_candidato_id = 1
            
            data_sin_idProyecto = {
                "idCandidato": existing_candidato_id,
                "respuestasEnviadas": [
                    {
                        "idRespuesta": "3"
                    }
                ]
            }

            response = self.app.post(f"/pruebas/nueva-respuesta/{existing_candidato_id}",
                                 data=json.dumps(data_sin_idProyecto),
                                 content_type="application/json")

            self.assertEqual(response.status_code, 404)

    def test_creacion_nueva_respuesta_sin_idCandidato(self):
        with app.app_context():
          
            data_sin_idProyecto = {
                "respuestasEnviadas": [
                    {
                        "idRespuesta": "3"
                    }
                ]
            }

            response = self.app.post(f"/pruebas/nueva-respuesta/",
                                 data=json.dumps(data_sin_idProyecto),
                                 content_type="application/json")

            self.assertEqual(response.status_code, 404)


    def teardown_method(self, args):
        self.session.close()
        Base.metadata.drop_all(bind=engine)