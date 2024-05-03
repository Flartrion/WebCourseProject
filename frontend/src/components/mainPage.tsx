import { useEffect, useReducer, useState } from "react";
import pageSelectorController from "../pageSelection/pageSelectorController";
import { Alert, Box, Container } from "@mui/material";
import pageSelectorModel from "../pageSelection/pageSelectorModel";
import Page from "../pageSelection/pageSelectionEnum";
import ItemPage from "../itemPage/itemPage";

function MainPage({ someProp }: any) {
  const [pageSelection, setPageSelection] = useState(
    pageSelectorModel.pageSelection
  );

  useEffect(() => {
    pageSelectorController.subscribeView("mainPage", setPageSelection);
    return () => {
      pageSelectorController.unsubscribeView("mainPage");
    };
  });

  let output: any;
  switch (pageSelection) {
    case Page.Items:
      output = <ItemPage />;
      break;

    case Page.Storages:
      output = (
        <Container>
          <Alert severity="info">Storages page W.I.P.</Alert>
        </Container>
      );
      break;

    case Page.Users:
      output = (
        <Container>
          <Alert severity="info">Users page W.I.P.</Alert>
        </Container>
      );
      break;

    case Page.Login:
      output = (
        <Container>
          <Alert severity="info">Login page W.I.P.</Alert>
        </Container>
      );
      break;

    default:
      output = (
        <Container>
          <Alert variant="filled" severity="error">
            Shit's on fire, yo!
          </Alert>
        </Container>
      );
  }

  return <>{output}</>;
}
export default MainPage;
