from .base_command import BaseCommannd
from ..models.pregunta import Pregunta, PreguntaSchema, CreatedPreguntaJsonSchema
from ..models.respuesta import Respuesta, RespuestaSchema, CreatedRespuestaJsonSchema
from ..session import Session


class CreatePregunta(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        session = Session()
        body: dict = self.data.copy()
        respuestas = body.pop("respuestas")
        print("RPESUETSAS")
        print(respuestas)

        posted_data = PreguntaSchema(
            only=(
                "idProyecto",
                "dificultad",
                "descripcion",
            )
        ).load(body)

        pregunta = Pregunta(**posted_data)

        session.add(pregunta)
        session.commit()
        new_pregunta = CreatedPreguntaJsonSchema().dump(pregunta)
        session.close()

        session = Session()
        for respuesta in respuestas:
            posted_data = RespuestaSchema(only=(["descripcion", "esCorrecta"])).load(
                respuesta
            )
            print("DATATATATA")
            print(posted_data)
            respuesta_db = Respuesta(**posted_data, idPregunta=pregunta.id)
            session.add(respuesta_db)
        session.commit()
        session.close()

        return new_pregunta
