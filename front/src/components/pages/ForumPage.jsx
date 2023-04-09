import ForumDiscussion from "../common/ForumDiscussion";

export default function ForumPage(props) {
    return (
        <div className="flex-shrink-0" style={{backgroundColor : 'rgb(255,255,255)'}}>
            <h2>Форум</h2>
            <div className="container-fluid column">
                <ForumDiscussion title="Обсуждение 1" username="Пользователь 1" text="Last updated 3 mins ago"/>
                <ForumDiscussion title="Обсуждение 2" username="Пользователь 2" text="Last updated 7 mins ago"/>
            </div>
        </div>
    );
}