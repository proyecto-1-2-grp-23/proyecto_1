from .base_command import BaseCommannd
from ..models.pregunta import Pregunta, PreguntaSchema, CreatedPreguntaJsonSchema
from ..models.respuesta import Respuesta, RespuestaSchema, CreatedRespuestaJsonSchema
from ..session import Session


class CreatePregunta(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            body: dict = self.data.copy()
            respuestas = body.pop("respuestas")

            posted_data = PreguntaSchema(
                only=(
                    "idProyecto",
                    "dificultad",
                    "descripcion",
                )
            ).load(body)

            pregunta = Pregunta(**posted_data)
            session = Session()
            session.add(pregunta)
            session.commit()
            for respuesta in respuestas:
                posted_data = RespuestaSchema(only=("descripcion",)).load(respuesta)
                respuesta_db = Respuesta(**posted_data, idPregunta=pregunta.id)
                session.add(respuesta_db)
            session.commit()
            new_pregunta = CreatedPreguntaJsonSchema().dump(pregunta)
            session.close()

            return new_pregunta

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
