import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv


external interface MainPageProps : RProps {
    var location: SiteLocation
}


data class LocationState(val location: SiteLocation) : RState

@JsExport
class MainPage() : RComponent<MainPageProps, LocationState>() {

    init {
        state = LocationState(SiteLocation.Main)
//        setState(LocationState(SiteLocation.Main))
    }

    override fun RBuilder.render() {

        styledDiv {
            header {
                onChangeLocation = {
                    setState(LocationState(it))
                }
            }

            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                }
                loginMenu()


                when (state.location) {
                    SiteLocation.Main -> generalBody()
                    SiteLocation.Contacts -> contactsPage()
                    SiteLocation.Schedule -> schedulePage()
                    SiteLocation.About -> aboutPage()
                    else -> generalBody()
                }

                styledDiv {
                    css {
                        padding(30.px)
                        display = Display.inlineBlock
                        paddingLeft = 10.px
                        paddingRight = 10.px
                        backgroundColor = Color.darkRed
                        width = 170.px
//                height = LinearDimension.maxContent
                    }
                }
            }


            footer {
                onChangeLocation = {
                    setState(LocationState(it))
                }
            }
        }
    }
}