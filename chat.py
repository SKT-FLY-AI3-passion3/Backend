import os
from google.cloud import dialogflowcx_v3 as dialogflow
import proto


def run_sample():
    # TODO(developer): Replace these values when running the function
    project_id = "vaulted-fort-358506"
    # For more information about regionalization see https://cloud.google.com/dialogflow/cx/docs/how/region
    location_id = "asia-northeast1"
    # For more info on agents see https://cloud.google.com/dialogflow/cx/docs/concept/agent
    agent_id = "f31dc17b-228d-4d2b-a0da-73f059ad997b"
    agent = f"projects/{project_id}/locations/{location_id}/agents/{agent_id}"
    # For more information on sessions see https://cloud.google.com/dialogflow/cx/docs/concept/session
    session_id = "me"
    texts = ["안녕","햄버거는 뭐가 있어요?"]
    # For more supported languages see https://cloud.google.com/dialogflow/es/docs/reference/language
    language_code = "ko-KR"

    detect_intent_texts(agent, session_id, texts, language_code)


def detect_intent_texts(agent, session_id, texts, language_code):
    """Returns the result of detect intent with texts as inputs.

    Using the same `session_id` between requests allows continuation
    of the conversation."""
    session_path = f"{agent}/sessions/{session_id}"
    print(f"Session path: {session_path}\n")
    client_options = None
    agent_components = dialogflow.AgentsClient.parse_agent_path(agent)
    location_id = agent_components["location"]
    if location_id != "global":
        api_endpoint = f"{location_id}-dialogflow.googleapis.com:443"
        print(f"API Endpoint: {api_endpoint}\n")
        client_options = {"api_endpoint": api_endpoint}
    session_client = dialogflow.SessionsClient(client_options=client_options)

    for text in texts:
        text_input = dialogflow.TextInput(text=text)
        query_input = dialogflow.QueryInput(text=text_input, language_code=language_code)
        request = dialogflow.DetectIntentRequest(
            session=session_path, query_input=query_input
        )
        response = session_client.detect_intent(request=request)
        # print(response.query_result)
        print("=" * 20)
        print(f"Query text: {response.query_result.text}")
        response_messages = [
            " ".join(msg.text.text) for msg in response.query_result.response_messages
        ]
        print(f"Response text: {' '.join(response_messages)}\n")

os.environ["GOOGLE_APPLICATION_CREDENTIALS"] ='application_default_credentials.json'
run_sample()