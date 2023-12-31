import unittest

from ..src.commands.create_equipo import CreateEquipo

from ..src.commands.create_user import CreateUser
from ..src.main import app
from ..src.models.candidato import Candidato, CandidatoSchema
from ..src.models.user import User, Base
from ..src.session import Session, engine
from ..src.models.model import Base
from ..src.session import Session, engine
from ..src.commands.create_entrevista import CreateEntrevista
from ..src.commands.list_entrevista import ListEntrevista
from ..src.commands.get_entrevista import GetEntrevista
import json

data = {
    "correo": "usuario324@ejemplo.com",
    "password": "34234",
    "ciudad": "Bogota",
    "pais": "Colombia",
    "telefono": "43534543",
    "nombreCompleto": "Carlos Arango",
    "edad": 34,
    "idiomas": "Español",
    "rasgosPersonalidad": "Sociable",
}


class TestMiApp(unittest.TestCase):
    def setUp(self):
        app.config["TESTING"] = True
        self.app = app.test_client()
        Base.metadata.create_all(engine)
        self.session = Session()
        self.data = {
            "correo": "usuario324@ejemplo.com",
            "password": "34234",
            "ciudad": "Bogota",
            "pais": "Colombia",
            "telefono": "43534543",
            "nombreCompleto": "Carlos Arango",
            "edad": 34,
            "idiomas": "Español",
            "rasgosPersonalidad": "Sociable",
        }

    def test_creacion_usuario(self):
        with app.app_context():
            if data:
                # Si el usuario existe, no debe poder crearse nuevamente
                response = self.app.post(
                    "/users", data=json.dumps(data), content_type="application/json"
                )
                if response.status_code == 201:
                    self.assertEqual(
                        response.status_code, 201
                    )  # Código 409 para indicar un conflicto
                else:
                    self.assertEqual(response.status_code, 412)

    def test_validacion_usuario_y_candidato(self):
        data = {
            "correo": "usuario324@ejemplo.com",
            "password": "34234",
            "ciudad": "Bogota",
            "pais": "Colombia",
            "telefono": "43534543",
            "nombreCompleto": "Carlos Arango",
            "edad": 34,
            "idiomas": "Español",
            "rasgosPersonalidad": "Sociable",
        }
        # Datos de ejemplo para la prueba
        with app.app_context():
            usuario_existente = (
                self.session.query(User).filter_by(correo=data["correo"]).first()
            )
            if usuario_existente:
                response = self.app.post(
                    "/users", data=json.dumps(data), content_type="application/json"
                )
                self.assertEqual(
                    response.status_code, 412
                )  # Código 409 para indicar un conflicto
                # self.assertEqual(response_data['error'], 'El usuario ya existe')
            else:
                # Si el usuario no existe, se puede crear
                response = self.app.post(
                    "/users", data=json.dumps(data), content_type="application/json"
                )
                self.assertEqual(response.status_code, 201)

    def test_validacion_varios_idiomas(self):
        data = {
            "correo": "usuario324@ejemplo.com",
            "password": "34234",
            "ciudad": "Bogota",
            "pais": "Colombia",
            "telefono": "43534543",
            "nombreCompleto": "Carlos Arango",
            "edad": 34,
            "idiomas": "Español",
            "rasgosPersonalidad": "Sociable",
        }
        # Verificar si el usuario ya existe en la base de datos
        with app.app_context():
            usuario_existente = (
                self.session.query(User).filter_by(correo=data["correo"]).first()
            )
            if usuario_existente:
                # Si el usuario existe, no se crea
                response = self.app.post(
                    "/users", data=json.dumps(data), content_type="application/json"
                )
                self.assertEqual(
                    response.status_code, 412
                )  # Código 409 para indicar un conflicto

            else:
                # Si el usuario no existe, se crea
                response = self.app.post(
                    "/users", data=json.dumps(data), content_type="application/json"
                )
                self.assertEqual(response.status_code, 201)

                # Se verifica que el usuario creado tiene los idiomas correctos
                nuevo_usuario = (
                    self.session.query(Candidato)
                    .filter_by(nombreCompleto=data["nombreCompleto"])
                    .first()
                )
                self.assertIsNotNone(nuevo_usuario)
                self.assertEqual(nuevo_usuario.idiomas, data["idiomas"])

    def test_creacion_equipo(self):
        # Datos necesarios para crear un equipo
        nombre_equipo = {
            "nombre": "Los vengadores8",
            "descripcion": "Nada",
            "idEmpresa": 1,
            "idFuncionario": 1,
        }

    #    # Crear una instancia de CreateUser y crear el equipo
    #    equipo = CreateEquipo(nombre_equipo).execute()

    #    # Verificar si el equipo fue creado correctamente
    #    self.assertTrue("id" in equipo)
    #    self.assertTrue("createdAt" in equipo)

    def test_crear_entrevista(self):
        # Datos necesarios para crear un equipo
        body = {
            "idFuncionario": 1,
            "idEmpresa": 1,
            "idCandidato": 1,
            "fecha": "2023-03-03 03:03:03",
            "lugar": "Edificio Alcalá 2",
        }

        # Crear una instancia de CreateUser y crear el equipo
        entrevista = CreateEntrevista(body).execute()
        # Verificar si el equipo fue creado correctamente
        self.assertTrue("id" in entrevista)
        self.assertTrue("resultado" in entrevista)
        self.assertTrue(entrevista["lugar"] == body["lugar"])

    def test_list_entrevista(self):
        # Datos necesarios para crear un equipo
        body = {
            "idFuncionario": 1,
            "idEmpresa": 1,
            "idCandidato": 1,
            "fecha": "2023-03-03 03:03:03",
            "lugar": "Edificio Alcalá 2",
        }

        # Crear una instancia de CreateUser y crear el equipo
        CreateEntrevista(body).execute()
        CreateEntrevista(body).execute()
        CreateEntrevista(body).execute()
        entrevistas = ListEntrevista().execute()
        # Verificar si el equipo fue creado correctamente
        self.assertTrue(len(entrevistas) == 3)
        self.assertTrue(entrevistas[0]["lugar"] == body["lugar"])

    def test_get_entrevista(self):
        # Datos necesarios para crear un equipo
        body = {
            "idFuncionario": 1,
            "idEmpresa": 1,
            "idCandidato": 1,
            "fecha": "2023-03-03 03:03:03",
            "lugar": "Edificio Alcalá 2",
        }

        # Crear una instancia de CreateUser y crear el equipo
        CreateEntrevista(body).execute()
        CreateEntrevista(body).execute()
        created = CreateEntrevista(body).execute()
        entrevista = GetEntrevista(created.get("id", 0)).execute()
        # Verificar si el equipo fue creado correctamente
        self.assertTrue(entrevista.get("id") == created.get("id"))
        self.assertTrue(entrevista)

    def teardown_method(self, args):
        self.session.close()
        Base.metadata.drop_all(bind=engine)
