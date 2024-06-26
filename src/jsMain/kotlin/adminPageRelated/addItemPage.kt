package adminPageRelated

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.xhr.FormData
import org.w3c.xhr.XMLHttpRequest
import react.*
import styled.*

external interface AddItemPageState : RState {
    var isbnInput: String
    var rlbcInput: String
    var titleInput: String
    var authorsInput: String
    var typeInput: String
    var detailsInput: String
    var languageInput: String
}

external interface AddItemPageProps : RProps {
    var onCompleteFunction: () -> Unit
}

@JsExport
class AddItemPage : RComponent<AddItemPageProps, AddItemPageState>() {
    override fun componentDidMount() {
        setState {
            isbnInput = ""
            rlbcInput = ""
            titleInput = ""
            authorsInput = ""
            typeInput = ""
            detailsInput = ""
            languageInput = ""
        }
    }

    override fun RBuilder.render() {
        styledTable {
            css {
                width = LinearDimension.fillAvailable
                children {
                    children {
                        textAlign = TextAlign.left
                        firstChild {
                            textAlign = TextAlign.right
                        }
                        input {
                            width = 500.px
                        }
                    }
                    lastChild {
                        children {
                            textAlign = TextAlign.center
                        }
                    }
                }
            }
            styledColGroup {
                styledCol {
                    css {
                        width = LinearDimension("30%")
                        textAlign = TextAlign.right
                    }
                }
                styledCol {
                    css {
                        textAlign = TextAlign.left
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"ISBN: " }
                }
                styledTd {
                    styledInput {
                        attrs {
                            onChangeFunction = {
                                setState { isbnInput = (it.target as HTMLInputElement).value }
                            }
                            type = InputType.text
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"ББК: " }
                }
                styledTd {
                    styledInput {
                        attrs {
                            onChangeFunction = {
                                setState { rlbcInput = (it.target as HTMLInputElement).value }
                            }
                            type = InputType.text
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"Название: " }
                }
                styledTd {
                    styledInput {
                        attrs {
                            onChangeFunction = {
                                setState { titleInput = (it.target as HTMLInputElement).value }
                            }
                            type = InputType.text
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"Авторы: " }
                }
                styledTd {
                    styledInput {
                        attrs {
                            onChangeFunction = {
                                setState { authorsInput = (it.target as HTMLInputElement).value }
                            }
                            type = InputType.text
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"Тип: " }
                }
                styledTd {
                    styledInput {
                        attrs {
                            onChangeFunction = {
                                setState { typeInput = (it.target as HTMLInputElement).value }
                            }
                            type = InputType.text
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"Язык: " }
                }
                styledTd {
                    styledInput {
                        attrs {
                            onChangeFunction = {
                                setState { languageInput = (it.target as HTMLInputElement).value }
                            }
                            type = InputType.text
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    styledLabel { +"Дополнительно: " }
                }
                styledTd {
                    styledTextArea {
                        attrs {
                            onChangeFunction = {
                                setState { detailsInput = (it.target as HTMLTextAreaElement).value }
                            }
                        }
                    }
                }
            }

            styledTr {
                styledTd {
                    attrs {
                        colSpan = "2"
                    }
                    styledInput {
                        attrs {
                            value = "Подтвердить"
                            type = InputType.submit
                            onClickFunction = {
                                val request = XMLHttpRequest()
                                request.open("post", "/items/insert")
                                request.onload = {
                                    if (request.status == 200.toShort()) {
                                        props.onCompleteFunction()
                                    } else {
                                        window.alert("Something went wrong!\nReason: ${request.response}")
                                    }
                                }
                                val data = FormData()
                                if (state.titleInput.isNotBlank())
                                    data.append("title", state.titleInput)
                                if (state.authorsInput.isNotBlank())
                                    data.append("authors", state.authorsInput)
                                if (state.typeInput.isNotBlank())
                                    data.append("type", state.typeInput)
                                if (state.isbnInput.isNotBlank())
                                    data.append("isbn", state.isbnInput)
                                if (state.rlbcInput.isNotBlank())
                                    data.append("rlbc", state.rlbcInput)
                                if (state.detailsInput.isNotBlank())
                                    data.append("details", state.detailsInput)
                                if (state.languageInput.isNotBlank())
                                    data.append("language", state.languageInput)
                                request.send(data)
                            }
                        }
                        css {
                            fontFamily = "Arial"
                            backgroundColor = Color("#999999")
                            color = Color.white
                            hover {
                                backgroundColor = Color("#cccccc")
                                color = Color.darkRed
                            }
                        }
                    }
                }
            }
        }
    }
}