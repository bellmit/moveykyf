package com.cyrj.pharmacymessage.db;

import android.net.Uri;
import android.provider.BaseColumns;

import com.cyrj.pharmacymessage.MyApplication;

/**
 * 数据库表辅助类
 */
public final class Database {

	public static final String AUTHORITY = MyApplication.getContext().getPackageName()+".provider";

	// This class cannot be instantiated
	private Database() {
	}

	public static final class User implements BaseColumns {
		// This class cannot be instantiated
		private User() {
		}

		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/t_user");

		/**
		 * The MIME type of {@link #CONTENT_URI} providing a directory of items.
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.bsoft.mob.ienr.user";

		/**
		 * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
		 * item.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.bsoft.mob.ienr.user";

		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = "_id ASC";

		/**
		 * 用户名
		 * <p/>
		 * Type: TEXT
		 * </P>
		 */
		public static final String USER_NAME = "user_name";

		/**
		 * 密码
		 * <p/>
		 * Type: TEXT
		 * </P>
		 */
		public static final String PASSWORD = "password";

		/**
		 * 员工ID
		 * <p/>
		 * Type: TEXT
		 * </P>
		 */
		public static final String REMOTE_ID = "remote_id";

		/**
		 * 员工所属机构ID
		 * <p/>
		 * Type: TEXT
		 * </P>
		 */
		public static final String AGENT_ID = "agent_id";

	}
}
